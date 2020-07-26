package renderEngine

import java.nio.FloatBuffer

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL15._
import org.lwjgl.opengl.GL20._

import scala.collection.mutable.ListBuffer

class Loader {
  var vaos = new ListBuffer[Int]()
  var vbos = new ListBuffer[Int]()

  def loadToVAO(positions: Array[Float]): RawModel = {
    val vaoID = createVAO()
    storeDataInAttrList(0,positions);
    unbindVAO()
    new RawModel(vaoID, positions.length / 3)
  }

  def cleanUp(): Unit = {
    vaos.foreach(glDeleteVertexArrays)
    vbos.foreach(glDeleteBuffers)
  }

  private def createVAO(): Int = {
    val vaoID = glGenVertexArrays();
    vaos += vaoID
    glBindVertexArray(vaoID)
    vaoID
  }

  private def storeDataInAttrList(attrNum: Int, data: Array[Float]): Unit = {
    val vboID =glGenBuffers()
    vbos += vboID
    glBindBuffer(GL_ARRAY_BUFFER, vboID)
    val buffer = storeDataInFloatBuffer(data)
    glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
    glVertexAttribPointer(
      attrNum,
      3,
      GL11.GL_FLOAT,
      false,
      0,
      0
    )
    glBindBuffer(GL_ARRAY_BUFFER, 0)
  }

  private def storeDataInFloatBuffer(data: Array[Float]): FloatBuffer = {
    val buffer = BufferUtils.createFloatBuffer(data.length)
    buffer.put(data).flip()
    buffer
  }

  private def unbindVAO(): Unit = {
    glBindVertexArray(0)
  }
}
