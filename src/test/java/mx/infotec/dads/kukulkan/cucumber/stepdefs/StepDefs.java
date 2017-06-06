package mx.infotec.dads.kukulkan.cucumber.stepdefs;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = KukulkancraftsmanApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
