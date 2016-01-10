package doctorWang

import scala.scalajs.js.annotation.JSExport
import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.HTMLSelectElement
import com.thoughtworks.binding.Binding
import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.Binding.Vars
import com.thoughtworks.binding.Binding.BindingSeq
import com.thoughtworks.binding.Binding.Constants

@JSExport
object Main {

  class Select(val options: BindingSeq[String], initialIndex: Int = 0) {

    val index = Var(initialIndex)

    @dom
    def render = {
      <select selectedIndex={ index.each } onchange={ event: Event =>
        index := event.target.asInstanceOf[HTMLSelectElement].selectedIndex
      }>
        {
          for {
            o <- options
          } yield {
            <option>{ o }</option>
          }
        }
      </select>
    }

  }

  @dom
  def page = {
    val select1Values = Seq(1, 3, 5)
    val select2Values = Seq(10, 30, 50)

    val select1StringValues = for { value <- select1Values } yield value.toString
    val select1Options = Constants(select1StringValues: _*)
    val select1 = new Select(select1Options)

    val select2 = new Select(Constants((for { value <- select2Values } yield value.toString): _*))

    <h1>调查问卷</h1>
    <h2>请选择第一个数字</h2>
    <div>{ select1.render.each }</div>
    <h2>请选择第二个数字</h2>
    <div>{ select2.render.each }</div>
    <p>两个数字之和是{
      (
        (select1.index.each match {
          case -1=> 0
          case i=> select1Values(i)
        }) +
        (select2.index.each match {
          case -1=> 0
          case i=> select2Values(i)
        })).toString
    }</p>
  }

  @JSExport
  def main(): Unit = {
    import org.scalajs.dom.document
    dom.render(document.body, page)
  }

}