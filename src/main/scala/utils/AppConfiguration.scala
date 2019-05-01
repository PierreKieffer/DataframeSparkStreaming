package utils

import java.io.{File, FileInputStream}
import java.util

import org.yaml.snakeyaml.Yaml

object AppConfiguration {
  private var props : java.util.HashMap[String,String] = new util.HashMap


  var inputPath = ""
  var schemaPath = ""
  var fileFormat = ""

  def initializeAppConfig(configFilePath : String) : Unit = {

    val fileInputStream = new FileInputStream(new File(configFilePath))
    val confObj = new Yaml().load(fileInputStream)

    props = confObj.asInstanceOf[java.util.HashMap[String,String]]
    inputPath = props.get("INPUT_PATH")
    schemaPath = props.get("JSON_SCHEMA_PATH")
    fileFormat = props.get("FORMAT")

  }

}
