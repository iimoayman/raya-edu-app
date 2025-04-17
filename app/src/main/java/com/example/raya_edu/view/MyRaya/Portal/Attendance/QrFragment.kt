package com.example.raya_edu.view.MyRaya.Portal.Attendance

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.MeteringPoint
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raya_edu.R
import com.example.raya_edu.model.repository.AttendanceRepository
import com.example.raya_edu.model.utils.observeOnce
import com.example.raya_edu.viewmodel.MyAttendanceViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class QrScannerFragment : Fragment(R.layout.qr_scanner) {

    private lateinit var previewView: PreviewView
    private lateinit var zoomInButton: Button
    private lateinit var zoomOutButton: Button
    private lateinit var camera: Camera
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var response: AttendanceRepository

    private lateinit var attendanceViewModel: MyAttendanceViewModel
    private val barcodeScanner = BarcodeScanning.getClient()

    private var isProcessing = false // Flag to prevent multiple scans

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.qr_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewView = view.findViewById(R.id.preview_view)
        zoomInButton = view.findViewById(R.id.zoom_in_button)
        zoomOutButton = view.findViewById(R.id.zoom_out_button)

        attendanceViewModel = ViewModelProvider(requireActivity()).get(MyAttendanceViewModel::class.java)

        cameraExecutor = Executors.newSingleThreadExecutor()

        if (hasCameraPermission()) {
            startCamera()
        } else {
            requestCameraPermission()
        }

        zoomInButton.setOnClickListener { adjustZoom(true) }
        zoomOutButton.setOnClickListener { adjustZoom(false) }

        // Tap-to-focus implementation
        previewView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val factory = previewView.meteringPointFactory
                val point = factory.createPoint(event.x, event.y)
                focusCameraAtPoint(point)
            }
            return@setOnTouchListener true
        }
    }

    private fun startCamera() {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
                processImageProxy(imageProxy)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @androidx.annotation.OptIn(ExperimentalGetImage::class)
    private fun processImageProxy(imageProxy: ImageProxy) {
        if (isProcessing) {
            imageProxy.close()
            return
        }

        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    barcode.rawValue?.let { scannedQrCode ->
                        handleScannedQrCode(scannedQrCode)
                    }
                }
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
    private fun handleScannedQrCode(scannedQrCode: String) {
        if (isProcessing) return
        isProcessing = true

        val PB = view?.findViewById<ProgressBar>(R.id.progressBar_3)
        PB?.visibility = View.VISIBLE

        val studentId = 3
        attendanceViewModel.markAttendance(studentId, scannedQrCode)

        // Remove previous observers to prevent multiple toasts
        attendanceViewModel.errorMessage.removeObservers(viewLifecycleOwner)
        attendanceViewModel.savedAttendance.removeObservers(viewLifecycleOwner)

        // Observe error messages ONCE
        attendanceViewModel.errorMessage.observeOnce(viewLifecycleOwner) { errorMessage ->
            PB?.visibility = View.GONE
            if (isAdded) { // Ensure the fragment is still attached
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
            isProcessing = false
        }

        // Observe success response ONCE
        attendanceViewModel.savedAttendance.observe(viewLifecycleOwner) { savedAttendance ->
            PB?.visibility = View.GONE
            if (isAdded) { // Ensure the fragment is still attached
                Toast.makeText(requireContext(), "Scanned", Toast.LENGTH_SHORT).show()
            }

            Handler(Looper.getMainLooper()).postDelayed({
                if (isAdded) {
                    requireActivity().supportFragmentManager.popBackStack()
                }
                isProcessing = false
            }, 500)
        }
    }


    private fun focusCameraAtPoint(point: MeteringPoint) {
        val focusAction = FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
            .setAutoCancelDuration(3, TimeUnit.SECONDS)
            .build()

        camera.cameraControl.startFocusAndMetering(focusAction)
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestPermissions.launch(Manifest.permission.CAMERA)
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                Snackbar.make(requireView(), "Camera permission required!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    private fun adjustZoom(zoomIn: Boolean) {
        val cameraControl = camera.cameraControl
        val zoomState = camera.cameraInfo.zoomState.value ?: return
        val newZoom = if (zoomIn) {
            (zoomState.zoomRatio + 0.5f).coerceAtMost(zoomState.maxZoomRatio)
        } else {
            (zoomState.zoomRatio - 0.5f).coerceAtLeast(zoomState.minZoomRatio)
        }
        cameraControl.setZoomRatio(newZoom)
    }

    override fun onPause() {
        super.onPause()
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                requireActivity().onBackPressed()
            }
        }
    }
}