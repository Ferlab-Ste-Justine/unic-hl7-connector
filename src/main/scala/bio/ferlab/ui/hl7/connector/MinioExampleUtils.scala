package bio.ferlab.ui.hl7.connector

import java.io.IOException
import java.io.InputStream
import java.util.Objects
import java.util.Properties


object MinioExampleUtils {
  @throws[IOException]
  def loadMinioPropertiesFile: Properties = {
    val properties = new Properties
    val fileName = "minio_key.properties"
    val inputStream = Objects.requireNonNull(classOf[MinioExampleUtils].getClassLoader.getResourceAsStream(fileName))
    properties.load(inputStream)
    properties
  }

  @throws[Exception]
  def loadMinioAccessFromJvmEnv: Properties = {
    val properties = new Properties
    if (System.getProperty("endpoint") == null || System.getProperty("accessKey") == null || System.getProperty("secretKey") == null) throw new Exception("Make sure to supply minio endpoint and credentials")
    properties.setProperty("endpoint", System.getProperty("endpoint"))
    properties.setProperty("access_key", System.getProperty("accessKey"))
    properties.setProperty("secret_key", System.getProperty("secretKey"))
    properties.setProperty("region", System.getProperty("region"))
    properties
  }
}

final class MinioExampleUtils private() {
}
