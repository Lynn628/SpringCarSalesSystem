
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lynn
 */
 @FacesValidator("emailValidator")
    public class emailValidation implements Validator{
        private Pattern pattern;
        private Matcher matcher;
        
        @Override
        public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
            String componentValue = value.toString();
            pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
            matcher = pattern.matcher("a@aa.com");
            if(!matcher.find()){
                String message = MessageFormat.format("Not valid email",componentValue);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong Email", "Not valid email address");
               throw new ValidatorException(facesMessage);
            }
        }
        
//     <h:selectOneMenu value="#{registerBean.userRegiste.type}" required="true">
//                     <f:selectItem itemValue="C"
//                                     itemLabel="Customer"/>
//                     <f:selectItem itemValue="S"
//                                    itemLabel="Sales Person"/>
//                 </h:selectOneMenu>
  }      