/*
 * Copyright (c) 2009, 2011, Oracle and/or its affiliates. All rights reserved.
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

import com.oracle.max.graal.graph.*;
import com.sun.cri.ci.*;

/**
 * The {@code NewArray} class is the base of all instructions that allocate arrays.
 */
public abstract class NewArray extends Instruction {

    private static final int INPUT_COUNT = 1;
    private static final int INPUT_LENGTH = 0;

    private static final int SUCCESSOR_COUNT = 0;

    @Override
    protected int inputCount() {
        return super.inputCount() + INPUT_COUNT;
    }

    @Override
    protected int successorCount() {
        return super.successorCount() + SUCCESSOR_COUNT;
    }

    /**
     * The instruction that produces the length of this array.
     */
     public Value length() {
        return (Value) inputs().get(super.inputCount() + INPUT_LENGTH);
    }

    public Value setLength(Value n) {
        return (Value) inputs().set(super.inputCount() + INPUT_LENGTH, n);
    }

    /**
     * Constructs a new NewArray instruction.
     * @param length the instruction that produces the length for this allocation
     * @param stateBefore the state before the allocation
     * @param inputCount
     * @param successorCount
     * @param graph
     */
    NewArray(Value length, int inputCount, int successorCount, Graph graph) {
        super(CiKind.Object, inputCount + INPUT_COUNT, successorCount + SUCCESSOR_COUNT, graph);
        setLength(length);
    }
}