package ru.snowreplicator.order_approving.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import ru.snowreplicator.order_approving.Service.Common.Events;
import ru.snowreplicator.order_approving.Service.Common.States;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.DRAFT)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.DRAFT).target(States.IN_PROGRESS).event(Events.SUBMIT)
                .and()
                .withExternal()
                .source(States.IN_PROGRESS).target(States.ACCEPTED).event(Events.COMPLETE)
                .and()
                .withExternal()
                .source(States.IN_PROGRESS).target(States.REJECTED).event(Events.REJECT);
    }

}