package renderEngine

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL20._

class Renderer {
  def prepare(): Unit = {
    glClearColor(0.8f,0.9f,1, 1)
    // glClearColor(204,229,255, 1)
    glClear(GL_COLOR_BUFFER_BIT);
  }

  def render(rawModel: RawModel) = {
    glBindVertexArray(rawModel.vaoID)
    glEnableVertexAttribArray(0)
    glDrawArrays(GL_TRIANGLES, 0, rawModel.vertexCount)
    glDisableVertexAttribArray(0)
    glBindVertexArray(0)
  }
}
