import java.io.File
import java.nio.file.{Files, Paths}

import akka.actor.{ActorSystem, Props}

import akka.testkit.TestKit

import com.actor.FolderScannerActor
import org.scalatest._
import org.scalatest.flatspec.{AnyFlatSpec, AnyFlatSpecLike}
import org.scalatest.matchers.must.Matchers




class TestingReadWrite extends TestKit(ActorSystem("test-system"))
  with AnyFlatSpecLike
  with BeforeAndAfterAll
  with Matchers{
  override def afterAll={
    TestKit.shutdownActorSystem(system)
    it should "read files " in{
      val scanner = system.actorOf(FolderScannerActor.props, "scanner")
      val directoryPath = getClass.getResource("/read_file").getPath
      scanner ! directoryPath
    }
    it should "write files " in{
      val path = "/home/knoldus/IdeaProjects/akka_file_read_write/write_files_here"
      val resultPath = Paths.get(path)
      if (Files.exists(resultPath))
        Files.delete(resultPath)
      Files.createFile(resultPath)

    }

  }

}
