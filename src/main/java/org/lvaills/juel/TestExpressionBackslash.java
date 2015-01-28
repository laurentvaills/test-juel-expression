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

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

public class TestExpressionBackslash {

    public static void main(String[] args) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        String expression = "foo${'\\\\'}${bar}";
        SimpleContext context = new SimpleContext();
        context.setVariable("bar", factory.createValueExpression("quix", String.class));
        ValueExpression expr = factory.createValueExpression(context, expression , String.class);

        System.out.println(expr.getValue(context));

    }

}
