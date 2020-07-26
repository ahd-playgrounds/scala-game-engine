package renderEngine

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL20._

class Renderer {
  def prepare(): Unit = {
    glClearColor(0.8f,0.9f,1, 1)
    glClear(GL_COLOR_BUFFER_BIT)
  }

  def render(rawModel: RawModel) = {
    glBindVertexArray(rawModel.vaoID)
    glEnableVertexAttribArray(0)
    glDrawElements(GL_TRIANGLES, rawModel.vertexCount, GL_UNSIGNED_INT, 0)
    glDisableVertexAttribArray(0)
    glBindVertexArray(0)
  }
}
