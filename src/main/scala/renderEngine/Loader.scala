package renderEngine

import java.nio.{FloatBuffer,IntBuffer}

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL15._
import org.lwjgl.opengl.GL20._

import scala.collection.mutable.ListBuffer

class Loader {
  import Helpers._
  var vaos = new ListBuffer[Int]()
  var vbos = new ListBuffer[Int]()

  def loadToVAO(positions: Array[Float], indices: Array[Int]): RawModel = {
    val vaoID = createVAO()
    bindIndicesBuffer(indices)
    storeDataInAttrList(0,positions);
    unbindVAO()
    new RawModel(vaoID, indices.length)
  }

  def cleanUp(): Unit = {
    vaos.foreach(glDeleteVertexArrays)
    vbos.foreach(glDeleteBuffers)
  }

  private def createVAO(): Int = {
    val vaoID = glGenVertexArrays()
    vaos += vaoID
    glBindVertexArray(vaoID)
    vaoID
  }

  private def createVBO(): Int = {
    val vboID = glGenBuffers()
    vbos += vboID
    vboID
  }

  private def storeDataInAttrList(attrNum: Int, data: Array[Float]): Unit = {
    glBindBuffer(GL_ARRAY_BUFFER, createVBO())
    glBufferData(GL_ARRAY_BUFFER, storeDataInBuffer(data), GL_STATIC_DRAW)
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

  private def unbindVAO(): Unit = {
    glBindVertexArray(0)
  }

  private def bindIndicesBuffer(indices: Array[Int]): Unit = {
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, createVBO())
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, storeDataInBuffer(indices), GL_STATIC_DRAW)
  }
}

object Helpers {
  def storeDataInBuffer(data: Array[Float]): FloatBuffer = {
    val buffer = BufferUtils.createFloatBuffer(data.length).put(data)
    buffer.flip()
    buffer
  }

  def storeDataInBuffer(data: Array[Int]): IntBuffer = {
    val buffer = BufferUtils.createIntBuffer(data.length).put(data)
    buffer.flip()
    buffer
  }
}
