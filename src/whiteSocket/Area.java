/**
*   WhiteSocket
*   Copyright (C) 2015-2017 - Dave Daggett - Blooprint, LLC
*
*   This program is free software; you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation; either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program; if not, write to the Free Software Foundation,
*   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
*/

package whiteSocket;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import whiteSocket.Bloop;

public class Area {

	int startX, startY, pxCount, minX, maxX, minY, maxY;
	boolean[][] area;
	
	static boolean[][] hasBeenHit;

	public Area(int x, int y) {

		this.startX = x;
		this.startY = y;
		this.area = new boolean[Bloop.sketch.getHeight()][Bloop.sketch.getWidth()];
		this.area = floodBorder(this, getBorder(this), this.startX, this.startY + 2);
//		getBorder(this);

	}// END constructor

	
	public static void getCornerBlobs() throws IOException {
		
//		ArrayList<Area> _1 = new ArrayList<Area>();

		System.out.println("getCornerBlobs() ...");

		hasBeenHit = new boolean[Bloop.sketch.getHeight()][Bloop.sketch.getWidth()];

		Color pxColor = null;

		here1:
		for (int row = 0; row < Bloop.sketch.getHeight()/2; row++) {
			for (int col = 0; col < Bloop.sketch.getWidth()/2; col++) {
				/*
				 * QUAD #1 - UL
				 */

				int xIN = col;
				int yIN = row;

				pxColor = new Color(Bloop.sketch.getRGB(xIN, yIN));
				if (Bloop.isMarker(pxColor) && !hasBeenHit[yIN][xIN]) {
					
					System.out.println("found a blob");
					
////						_1.add(new Area(xIN,yIN));
//						Area some = new Area(xIN,yIN);
					

					int[] inCoord = new int[2];
					inCoord[0] = xIN;
					inCoord[1] = yIN;
					boolean flag = true;
					while (flag) {

						// 2dArray[y][x]
						hasBeenHit[inCoord[1]][inCoord[0]] = true;

						inCoord = Border.getNextBorderPixel(inCoord);
						
						if ((inCoord[0] == xIN) && (inCoord[1] == yIN)) {
							System.out.println("found whole corner blob");

							flag = false;
							break here1;
						}
					}
				}
			}
		}
		
		here2:
		for (int row = 0; row < Bloop.sketch.getHeight()/2; row++) {
			for (int col = Bloop.sketch.getWidth()-1; col > Bloop.sketch.getWidth()/2; col--) {
				/*
				 * QUAD #2 - UR
				 */
				int xIN = col;
				int yIN = row;

				pxColor = new Color(Bloop.sketch.getRGB(xIN, yIN));
				if (Bloop.isMarker(pxColor) && !hasBeenHit[yIN][xIN]) {
					
					System.out.println("found a blob");
					
////						_1.add(new Area(xIN,yIN));
//						Area some = new Area(xIN,yIN);
					

					int[] inCoord = new int[2];
					inCoord[0] = xIN;
					inCoord[1] = yIN;
					boolean flag = true;
					while (flag) {

						// 2dArray[y][x]
						hasBeenHit[inCoord[1]][inCoord[0]] = true;

						inCoord = Border.getNextBorderPixel(inCoord);
						
						if ((inCoord[0] == xIN) && (inCoord[1] == yIN)) {
							System.out.println("found whole corner blob");

							flag = false;
							break here2;
						}
					}
				}
			}
		}
		
		here3:
		for (int row = Bloop.sketch.getHeight()-1; row > Bloop.sketch.getHeight()/2; row--) {
			for (int col = 0; col < Bloop.sketch.getWidth()/2; col++) {
				/*
				 * QUAD #2 - UR
				 */
				int xIN = col;
				int yIN = row;

				pxColor = new Color(Bloop.sketch.getRGB(xIN, yIN));
				if (Bloop.isMarker(pxColor) && !hasBeenHit[yIN][xIN]) {
					
					new Border(xIN,yIN);
					
//					System.out.println("found a blob");
//					
//////						_1.add(new Area(xIN,yIN));
////						Area some = new Area(xIN,yIN);
//					
//
//					int[] inCoord = new int[2];
//					inCoord[0] = xIN;
//					inCoord[1] = yIN;
//					boolean flag = true;
//					while (flag) {
//
//						// 2dArray[y][x]
//						hasBeenHit[inCoord[1]][inCoord[0]] = true;
//
//						inCoord = getNextBorderPixel(inCoord);
//						
//						if ((inCoord[0] == xIN) && (inCoord[1] == yIN)) {
//							System.out.println("found whole corner blob");
//
//							flag = false;
//							break here3;
//						}
//					}
					
					break here3;
				}
			}
		}
		
		here4:
		for (int row = Bloop.sketch.getHeight()-1; row > Bloop.sketch.getHeight()/2; row--) {
			for (int col = Bloop.sketch.getWidth()-1; col > Bloop.sketch.getWidth()/2; col--) {
				/*
				 * QUAD #2 - UR
				 */
				int xIN = col;
				int yIN = row;

				pxColor = new Color(Bloop.sketch.getRGB(xIN, yIN));
				if (Bloop.isMarker(pxColor) && !hasBeenHit[yIN][xIN]) {
					
					System.out.println("found a blob");
					
////						_1.add(new Area(xIN,yIN));
//						Area some = new Area(xIN,yIN);
					

					int[] inCoord = new int[2];
					inCoord[0] = xIN;
					inCoord[1] = yIN;
					boolean flag = true;
					while (flag) {

						// 2dArray[y][x]
						hasBeenHit[inCoord[1]][inCoord[0]] = true;

						inCoord = Border.getNextBorderPixel(inCoord);
						
						if ((inCoord[0] == xIN) && (inCoord[1] == yIN)) {
							System.out.println("found whole corner blob");

							flag = false;
							break here4;
						}
					}
				}
			}
		}
			
		printImgBool(hasBeenHit);

	}// END getCornerBlobs()

	public static boolean[][] getBorder(Area area) {
		
		System.out.println("getting border ...");
		/**
		 * dealing with area drawn by user to erase sets binary map single pixel
		 * strand border for future use in floodBorder() method
		 */

		boolean[][] border = new boolean[Bloop.sketch.getHeight()][Bloop.sketch.getWidth()];

		/*
		 * encapsulate eraser area
		 */
		int[] inCoord = new int[2];
		inCoord[0] = area.startX;
		inCoord[1] = area.startY;
		boolean flag = true;

		while (flag) {

			area.pxCount++;
			area.area[inCoord[1]][inCoord[0]] = true;
			
			hasBeenHit[inCoord[1]][inCoord[0]] = true;
			
//			Bloop.totalErase[inCoord[1]][inCoord[0]] = true;
			
			// 2dArray[y][x]
			border[inCoord[1]][inCoord[0]] = true;
			
			inCoord = Border.getNextBorderPixel(inCoord);
			
			if ((inCoord[0] == area.startX) && (inCoord[1] == area.startY)) {
				
				System.out.println("found whole border");
				
				flag = false;
			}
		}
		
		return border;
	}// END getBorder()

	public static boolean[][] floodBorder(Area area, boolean[][] floodArea, int x, int y) {
		/**
		 * paint bucket-like algorithm to fill binary map border this filled
		 * area becomes the area to be filtered through the stretch() area
		 * pixels to be turned Color.WHITE (unless notified otherwise by user
		 * dictation)
		 *
		 */

		if (!floodArea[y][x]) {

			Queue<Point> queue = new LinkedList<Point>();
			queue.add(new Point(x, y));

			while (!queue.isEmpty()) {

				Point p = queue.remove();

				if (!floodArea[p.y][p.x]) {

					floodArea[p.y][p.x] = true;

//					Bloop.totalErase[p.y][p.x] = true;
					hasBeenHit[p.y][p.x] = true;
					area.area[p.y][p.x] = true;
					area.pxCount++;

					queue.add(new Point(p.x + 1, p.y));
					queue.add(new Point(p.x - 1, p.y));
					queue.add(new Point(p.x, p.y + 1));
					queue.add(new Point(p.x, p.y - 1));

				}
			}
		}

		return floodArea;
	}// END floodBorder()
	
	/**
	 * TESTS: borders/areas
	 * */
	static BufferedImage testOut = new BufferedImage(Bloop.sketch.getWidth(), Bloop.sketch.getHeight(), BufferedImage.TYPE_INT_RGB);
	public static void createWhiteImage(){ 
		Graphics2D    graphics = testOut.createGraphics();	
		graphics.setPaint ( Color.white );
		graphics.fillRect ( 0, 0, testOut.getWidth(), testOut.getHeight() );		
	}//END createWhiteImage
	
	public static void printImgBool(boolean[][] some) throws IOException {
		createWhiteImage();
		for (int row = 0; row < Bloop.sketch.getHeight(); row++) {
			for (int col = 0; col < Bloop.sketch.getWidth(); col++) {
				if(some[row][col]) {
					testOut.setRGB(col, row, 0x000000);
				}
			}
		}
		saveImg(testOut);
	}//END printImgBool()
	
	public static void saveImg(BufferedImage some) throws IOException {
		try {
			File outputfile = new File("./tests/border");
			ImageIO.write(some, "bmp", outputfile);
		} catch (Exception ex) {
			System.out.println("ERROR saveBlooprint(): " + ex.getMessage());
		}
	}// END saveImg()
	
}