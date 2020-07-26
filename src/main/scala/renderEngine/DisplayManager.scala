package renderEngine

import org.lwjgl.LWJGLException
import org.lwjgl.opengl.{ContextAttribs, Display, DisplayMode, PixelFormat}
import org.lwjgl.opengl.GL11._;

class DisplayManager(val width: Int, val height: Int, val fps_cap: Int) {

  val attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);

  def createDisplay(): Unit = {
    try {
      Display.setDisplayMode(new DisplayMode(width, height))
      Display.create(new PixelFormat(), attribs)
      Display.setTitle("🦄")
    } catch {
      case e: LWJGLException => e.printStackTrace();
    }
    glViewport(0,0, width, height);
  }

  def updateDisplay(): Unit = {
    Display.sync(fps_cap);
    Display.update();
  }

  def closeDisplay(): Unit = {
    Display.destroy();
  }
}
