package org.legendtv.test;

import java.awt.BorderLayout;
import java.nio.ByteBuffer;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JFrame;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;

import org.legendtv.core.VideoJ3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Java3DTest extends JFrame
{
	private	Canvas3D		canvas;
	private SimpleUniverse	univ;
	
	public Java3DTest()
	{
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 600, 600);
		
		canvas	= new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		univ	= new SimpleUniverse(canvas);
		
		this.add(canvas, BorderLayout.CENTER);
		
		createSceneGraph();
		
		Transform3D	camTrans	= new Transform3D();
		
		camTrans.setTranslation(new Vector3d(0.0f, 0.0f, 13.0f));
		
		univ.getViewer().getView().setBackClipDistance(200.0f);
		univ.getViewingPlatform().setNominalViewingTransform();
		univ.getViewingPlatform().getViewPlatformTransform().setTransform(new Transform3D(camTrans));
		
		this.setVisible(true);
	}
	
	private void createSceneGraph()
	{
		//	Create the root of the branch graph 
		BranchGroup		objRoot		= new BranchGroup();
		final VideoJ3D	j3d			= new VideoJ3D(64, 64);
		QuadArray		shape;
		Appearance		app;
		
		shape	= new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);

		// Top left
		shape.setTextureCoordinate(0, 3, new TexCoord2f(0.0f, 0.0f));
		shape.setCoordinate(3, new Point3f(-5.0f, 5.0f, 0.0f));
		
		// Top Right
		shape.setTextureCoordinate(0, 2, new TexCoord2f(1.0f, 0.0f));
		shape.setCoordinate(2, new Point3f(5.0f, 5.0f, 0.0f));
		
		// Bottom Right
		shape.setTextureCoordinate(0, 1, new TexCoord2f(1.0f, 1.0f));
		shape.setCoordinate(1, new Point3f(5.0f, -5.0f, 0.0f));
		
		// Bottom Left
		shape.setTextureCoordinate(0, 0, new TexCoord2f(0.0f, 1.0f));
		shape.setCoordinate(0, new Point3f(-5.0f, -5.0f, 0.0f));
		
		genTexture(j3d);
		
		app		= j3d.getAppearance();
		
		app.setTransparencyAttributes(
				new TransparencyAttributes(
						TransparencyAttributes.NICEST,
						0.0f,
						TransparencyAttributes.BLEND_SRC_ALPHA,
						TransparencyAttributes.BLEND_SRC_ALPHA));
		
		app.setTexCoordGeneration(
				new TexCoordGeneration(
						TexCoordGeneration.OBJECT_LINEAR,
						TexCoordGeneration.TEXTURE_COORDINATE_3));
		
		objRoot.addChild(new Shape3D(shape, app));
		objRoot.compile();
		univ.addBranchGraph(objRoot);
		
		(new Thread()
		{
			public void run()
			{
				while (true)
				{
					genTexture(j3d);
					
					try
					{
						sleep(100);
					}
					
					catch (InterruptedException e)
					{
					}
				}
			}
		}).start();
	}
	
	private void genTexture(VideoJ3D j3d)
	{
		ByteBuffer	buf	= j3d.getVideoByteBuffer();
		
		buf.rewind();
		
		for (int i = 0; i < buf.capacity(); ++i)
		{
			buf.put(i, (byte)(Byte.MAX_VALUE * Math.random()));
		}
		
		j3d.render();
	}
	
	public static void main(String[] args)
	{
		new Java3DTest();
	}
}
