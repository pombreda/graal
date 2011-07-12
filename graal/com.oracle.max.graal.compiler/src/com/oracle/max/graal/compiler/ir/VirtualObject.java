/*
 * Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.max.graal.compiler.ir;

import java.util.*;

import com.oracle.max.graal.compiler.debug.*;
import com.oracle.max.graal.compiler.phases.EscapeAnalysisPhase.EscapeField;
import com.oracle.max.graal.graph.*;
import com.sun.cri.ci.*;
import com.sun.cri.ri.*;


public class VirtualObject extends FloatingNode {

    private static final int INPUT_COUNT = 0;

    private static final int SUCCESSOR_COUNT = 0;

    @Override
    protected int inputCount() {
        return super.inputCount() + INPUT_COUNT;
    }

    @Override
    protected int successorCount() {
        return super.successorCount() + SUCCESSOR_COUNT;
    }

    private EscapeField[] fields;
    private RiType type;

    public VirtualObject(RiType type, EscapeField[] fields, Graph graph) {
        super(CiKind.Int, INPUT_COUNT, SUCCESSOR_COUNT, graph);
        this.type = type;
        this.fields = fields;
    }

    public RiType type() {
        return type;
    }

    public EscapeField[] fields() {
        return fields;
    }

    @Override
    public void accept(ValueVisitor v) {
        // nothing to do...
    }

    @Override
    public Map<Object, Object> getDebugProperties() {
        Map<Object, Object> properties = super.getDebugProperties();
        properties.put("type", type);
        return properties;
    }

    @Override
    public String shortName() {
        return "VirtualObject " + type.name();
    }

    @Override
    public void print(LogStream out) {
        out.print("virtualobject ").print(type.name());
    }

    @Override
    public Node copy(Graph into) {
        VirtualObject x = new VirtualObject(type, fields, into);
        return x;
    }
}