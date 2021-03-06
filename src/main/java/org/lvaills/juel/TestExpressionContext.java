/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.lvaills.juel;

import static java.util.Arrays.*;

import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

public class TestExpressionContext {

    public static void main(String[] args) {
        ExpressionFactory factory = new ExpressionFactoryImpl();

        SimpleContext createContext = new SimpleContext();
        createContext.setVariable("myBean", factory.createValueExpression(new MyBean("Laurent"), MyBean.class));

        String expression;
        expression = "${myBean.foo} ${exchange.host}";

        ValueExpression expr = factory.createValueExpression(createContext, expression, String.class);
        System.out.println("expr Class : " + expr.getClass());


        SimpleContext evalContext = new SimpleContext();
        List<String> hosts = asList("www.example.com", "www.foo.com", "www.bar.com");

        // I want to evaluate the same expression, but with different values for the variable exchange.
        for (String host : hosts) {
            evalContext.setVariable("exchange", factory.createValueExpression(new MyExchange(host), MyExchange.class));
            System.out.println(expression + " = " + expr.getValue(evalContext));
        }
    }

}
