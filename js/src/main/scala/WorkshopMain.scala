import scala.scalajs.js.annotation.JSExport

import au.com.realcommercial.binding.Binding
import au.com.realcommercial.binding.dom

import au.com.realcommercial.binding.Binding._

import au.com.realcommercial.binding.Binding.Var
import au.com.realcommercial.binding.Binding.Vars
import au.com.realcommercial.binding.Binding.BindingSeq

import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.Node
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.html.Table

@JSExport
object WorkshopMain {

  @dom
  def table(initialTitle: String): Binding[BindingSeq[Node]] = {
    val title = Var(initialTitle)

    <h3>{ title.each }</h3>
    <button onclick={ event: Event => title := title.get + "<a>" }>Modify title</button>
    <table border="1" cellPadding="5">
      <tbody>
        <tr>
          <td>Operation</td>
          <td>{ title.each }</td>
        </tr>
      </tbody>
    </table>
  }

  @dom
  def page = {
    val data: Var[String] = Var("initial data")
    <section>
      { data.each }
      <button onclick={ event: Event => data := "Modified" }>Modify data</button>
      <h3>Header 3</h3>
      { table(data.each).each }
    </section>
  }

  @JSExport
  def main(): Unit = {
    import org.scalajs.dom.document
    dom.render(document.body, page)
  }
}
