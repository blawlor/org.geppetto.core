/*******************************************************************************
 * The MIT License (MIT)
 * 
 * Copyright (c) 2011, 2013 OpenWorm.
 * http://openworm.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *     	OpenWorm - http://openworm.org/people.html
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE 
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package org.geppetto.core.model.state;

import java.net.URL;
import java.util.List;

import org.geppetto.core.model.IModelInterpreter;
import org.geppetto.core.simulator.ISimulator;
import org.geppetto.core.visualisation.model.VisualModel;

public class AspectNode extends ACompositeStateNode{

	private String id;
	private TimeNode time;
	private IModelInterpreter modelInterpreter;
	private ISimulator simulator;
	private List<VisualModel> visualModels;
	private String instancePath;
	
	public TimeNode getTime() {
		return time;
	}

	public void setTime(TimeNode time) {
		this.time = time;
	}

	public IModelInterpreter getModelInterpreter() {
		return modelInterpreter;
	}

	public void setModelInterpreter(IModelInterpreter modelInterpreter) {
		this.modelInterpreter = modelInterpreter;
	}

	public ISimulator getSimulator() {
		return simulator;
	}

	public void setSimulator(ISimulator simulator) {
		this.simulator = simulator;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public ANode getParentEntity() {
		return this.getParent();
	}

	public List<VisualModel> getVisualModel() {
		return this.visualModels;
	}

	public void setInstancePath(String instancePath) {
		this.instancePath = instancePath;
	}

	public String getInstancePath() {
		return this.instancePath;
	}
	
}