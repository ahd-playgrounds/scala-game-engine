package gameTester

import org.lwjgl.opengl.Display
import renderEngine.{DisplayManager, Loader, Renderer}

object Game extends App {
  val display = new DisplayManager(800, 400, 60);
  display.createDisplay();

  val loader = new Loader()
  val renderer = new Renderer()

  val vertices = Array(-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f, -0.5f, 0.5f, 0f)
  val indices = Array(0, 1, 3, 3, 1, 2)

  val model = loader.loadToVAO(vertices, indices)

  while(!Display.isCloseRequested()) {
    renderer.prepare()
    renderer.render(model)
    display.updateDisplay();
  }

  loader.cleanUp()

  display.closeDisplay();
}
