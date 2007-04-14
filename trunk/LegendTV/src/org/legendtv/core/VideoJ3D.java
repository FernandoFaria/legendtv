package org.legendtv.core;

import java.nio.ByteBuffer;

import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.NioImageBuffer;
import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.NioImageBuffer.ImageType;

public class VideoJ3D
{
	private int					width,
								height;
	private Appearance			videoApp;
	private Texture2D			tex;
	private ImageComponent2D	videoComp;
	private NioImageBuffer		videoImgBuf;
	
	public VideoJ3D(int width, int height)
	{
		this.width	= width;
		this.height	= height;
		
		setupFrameTexture();
	}
	
	private void setupFrameTexture()
	{
		ByteBuffer		videoBBuf;
		
		videoApp	= new Appearance();
		videoBBuf	= ByteBuffer.allocateDirect(4 * width * height);
		videoImgBuf	= new NioImageBuffer(width, height, ImageType.TYPE_4BYTE_RGBA, videoBBuf);
		videoComp	= new ImageComponent2D(ImageComponent.FORMAT_RGBA, videoImgBuf, true, true);
		tex			= new Texture2D(Texture.BASE_LEVEL, Texture.RGB, width, height);
		
		//tex.setMinFilter(Texture2D.NICEST);
		//tex.setMagFilter(Texture2D.NICEST);
		
		videoComp.setCapability(ImageComponent.ALLOW_IMAGE_READ);
		videoComp.setCapability(ImageComponent.ALLOW_IMAGE_WRITE);
		
		tex.setCapability(Texture.ALLOW_IMAGE_WRITE);
		tex.setImage(0, videoComp);
		
		videoApp.setTexture(tex);
	}
	
	public Appearance getAppearance()
	{
		return (videoApp);
	}
	
	public ByteBuffer getVideoByteBuffer()
	{
		ByteBuffer		retVal;
		
		retVal	= (ByteBuffer)videoImgBuf.getDataBuffer();
		
		return (retVal);
	}
	
	public void render()
	{
		videoComp.set(videoImgBuf);
		tex.setImage(0, videoComp);
	}
}
