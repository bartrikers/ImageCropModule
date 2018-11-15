// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package imagecrop.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import imagecrop.implementation.ImageUtil;

public class CropImage extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __cropImgObj;
	private imagecrop.proxies.CropImage cropImgObj;
	private java.lang.Long imageWidth;
	private java.lang.Long imageHeight;
	private java.lang.Long thumbnailWidth;
	private java.lang.Long thumbnailHeight;

	public CropImage(IContext context, IMendixObject cropImgObj, java.lang.Long imageWidth, java.lang.Long imageHeight, java.lang.Long thumbnailWidth, java.lang.Long thumbnailHeight)
	{
		super(context);
		this.__cropImgObj = cropImgObj;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.thumbnailWidth = thumbnailWidth;
		this.thumbnailHeight = thumbnailHeight;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.cropImgObj = __cropImgObj == null ? null : imagecrop.proxies.CropImage.initialize(getContext(), __cropImgObj);

		// BEGIN USER CODE
		int cropHeight = this.imageHeight.intValue();
		int cropWidth = this.imageWidth.intValue();

		int x1 = this.cropImgObj.getcrop_x1();
		int y1 = this.cropImgObj.getcrop_y1();
		int x2 = this.cropImgObj.getcrop_x2();
		int y2 = this.cropImgObj.getcrop_y2();

		if (x2 > 0 && y2 > 0) {
			if (cropHeight == 0 && cropWidth == 0) {
				cropHeight = this.cropImgObj.getcrop_height();
				cropWidth = this.cropImgObj.getcrop_width();
			} else if (cropHeight == 0) {
				float ratio = (float) cropWidth / this.cropImgObj.getcrop_width().floatValue();
				cropHeight = Math.round(this.cropImgObj.getcrop_height() * ratio);
			} else if (cropWidth == 0) {
				float ratio = (float) cropHeight / this.cropImgObj.getcrop_height().floatValue();
				cropWidth = Math.round(this.cropImgObj.getcrop_width() * ratio);
			}
			
			ImageUtil.processImage(this.getContext(), cropImgObj.getMendixObject(),  cropWidth,  cropHeight, thumbnailWidth.intValue(), thumbnailHeight.intValue(), true, x1, y1, x2, y2);
			
			return true;
		} else
			return false;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "CropImage";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
