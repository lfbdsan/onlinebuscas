package br.com.onlinebuscas.conversores;

import java.io.Serializable;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("generic")
public class GenericConverter
  implements Converter, Serializable
{
  private static final long serialVersionUID = 1L;
  
  public Object getAsObject(FacesContext ctx, UIComponent component, String value)
  {
    if (value != null) {
      return getAttributesFrom(component).get(value);
    }
    return null;
  }
  
  public String getAsString(FacesContext ctx, UIComponent component, Object value)
  {
    if ((value != null) && 
      (!"".equals(value)))
    {
      SampleEntity entity = (SampleEntity)value;
      

      addAttribute(component, entity);
      
      Long codigo = entity.getId();
      if (codigo != null) {
        return String.valueOf(codigo);
      }
    }
    return (String)value;
  }
  
  protected void addAttribute(UIComponent component, SampleEntity o)
  {
    String key = o.getId().toString();
    getAttributesFrom(component).put(key, o);
  }
  
  protected Map<String, Object> getAttributesFrom(UIComponent component)
  {
    return component.getAttributes();
  }
  
  public static abstract interface SampleEntity
  {
    public abstract Long getId();
  }
}
