import utest._
import org.scalajs.dom.document
import com.thoughtworks.binding.dom
import com.thoughtworks.binding.Binding.Vars
import org.scalajs.dom.html.Quote
import org.scalajs.dom.html.Button

object TagPickerTestSuite extends TestSuite {
  val tests = TestSuite {
    'TagPickerShouldSupportTwoWayBinding{

      val tagPicker = new TagPicker(Vars("tag0", "tag1"))
      assert(tagPicker.tags.get.length == 2)

      dom.render(document.body, tagPicker.render)

      assert(document.getElementsByTagName("q").length == 2)
      assert(document.body.childNodes.length == 1)

      val xButtons = document.getElementsByTagName("q")(0).asInstanceOf[Quote].getElementsByTagName("button")
      assert(xButtons.length == 1)
      xButtons(0).asInstanceOf[Button].onclick(null)

      assert(document.getElementsByTagName("q").length == 1)
      assert(tagPicker.tags.get.length == 1)
      val tag1 = document.getElementsByTagName("q")(0).asInstanceOf[Quote]
      val tag1Text = tag1.textContent
      assert(tag1Text.trim.startsWith("tag1"))

      tagPicker.tags.get += "tag2"
      assert(tagPicker.tags.get.length == 2)
      println(tagPicker.tags.get)
      assert(tagPicker.tags.get(0) == "tag1")
      assert(tagPicker.tags.get(1) == "tag2")
      assert(document.getElementsByTagName("q").length == 2)

      "tag3" +=: tagPicker.tags.get
      assert(tagPicker.tags.get.length == 3)
      assert(tagPicker.tags.get(0) == "tag3")
      assert(tagPicker.tags.get(1) == "tag1")
      assert(tagPicker.tags.get(2) == "tag2")
      assert(document.getElementsByTagName("q").length == 3)

      assert(document.getElementsByTagName("q")(1) == tag1)

    }
  }

}