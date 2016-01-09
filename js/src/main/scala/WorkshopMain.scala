import scala.scalajs.js.annotation.JSExport

import com.thoughtworks.binding.Binding
import com.thoughtworks.binding.dom

import com.thoughtworks.binding.Binding._

import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.Binding.Vars
import com.thoughtworks.binding.Binding.BindingSeq

import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.Node
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.html.Table
import org.scalajs.dom.html.Input

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
    val tags: Vars[String] = Vars("tag1", "tag2-from-server-side")
    <section>
      <hr/>
      {
        tagPicker(tags).each
      }
      <button onclick={ event:Event =>
        println(tags.get)
      }>Submit</button>
    </section>
  }

  @JSExport
  def main(): Unit = {
    import org.scalajs.dom.document
    dom.render(document.body, page)
  }

  @dom
  def tagPicker(tags: Vars[String]): Binding[Node] = {
    val input: Input = <input/>;
    <section>
      <div>
        {
          for (tag <- tags) yield {
            <q>
              { tag }
            	<button onclick={ event: Event =>
            	  tags.get -= tag
            	}>x</button>
						</q>
          }
        }
      </div>
      { input }
      <button onclick={ event: Event =>
        if (input.value != "" && !tags.get.contains(input.value)) {
          tags.get += input.value
          input.value = ""
        }
      }>Add</button>
    </section>
  }

}
