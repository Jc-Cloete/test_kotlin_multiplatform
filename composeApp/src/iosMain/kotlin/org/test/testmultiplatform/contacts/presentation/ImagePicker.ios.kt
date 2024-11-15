package org.test.testmultiplatform.contacts.presentation

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo

actual class ImagePicker(
    private val rootController: UIViewController
) {

    private val imagePickerController = UIImagePickerController().apply {
        sourceType = UIImagePickerControllerSourceType.UIImagPickerControllerSourceTypePhotoLibrary
    }
    private var onImagePicked: ((ByteArray) -> Unit)? = null

    @OptIn(ExperimentalForeignApi::class)
    private val delegate = object: NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegate {
        override fun imagePickerController(
            picker: UIImagePickerController,
            didFinishPickingImage: UIImage,
            editingInfo: Map<Any?, *>?
        ){
            val imageNsData = UIImageJPEGRepresentation(didFinishPickingImage, 1.0) ?: return
            val bytes = ByteArray(imageNsData.length.toInt())
            memcpy(bytes.refTo(0), imageNsData.bytes, imageNsData.length)

            onImagePicked?.invoke(bytes)

            picker.dismissViewControllerAnimated(true, completion = null)
        }

        override fun imagePickerControllerDidCancel(picker: UIImagePickerController){

            picker.dismissViewControllerAnimated(true, completion = null)
        }
    }

    @Composable
    actual fun registerPicker(onImagePicked: (ByteArray) -> Unit) {
        this.onImagePicked = onImagePicked
    }

    actual fun pickImage() {
        rootController.presentViewController(imagePickerController, animated = true, completion = null) {
            imagePickerController.delegate = delegate
        }
    }

}