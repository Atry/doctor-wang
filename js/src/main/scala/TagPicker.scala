
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
import org.scalajs.dom.html.Input

class TagPicker(val tags: Vars[String] = Vars.empty[String]) {
  
  @dom
  def render: Binding[Node] = {
    val input: Input = <input/>;
    <section>
      <div>
        {
          for (tag <- tags) yield {
            <q className="tag">
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