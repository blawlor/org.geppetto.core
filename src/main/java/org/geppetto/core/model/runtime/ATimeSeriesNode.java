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
package org.geppetto.core.model.runtime;

import java.util.ArrayList;
import java.util.List;

import org.geppetto.core.model.quantities.PhysicalQuantity;

/**
 * Abstract node used for nodes that don't have other nodes as children, a leaf node.
 * 
 * @author matteocantarelli
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class ATimeSeriesNode extends ANode
{
	//A state variable has intrinsic dynamics that allow for it to change as part of the evolution of the model.
	private List<PhysicalQuantity> _timeSeries = new ArrayList<PhysicalQuantity>();;

	public ATimeSeriesNode(String name)
	{
		super(name);
	}
	
	@Override
	public String toString()
	{
		return _name + "[" + _timeSeries + "]";
	}

	public void addPhysicalQuantity(PhysicalQuantity value)
	{
		_timeSeries.add(value);
	}

	public List<PhysicalQuantity> getTimeSeries()
	{
		return _timeSeries;
	}

	public PhysicalQuantity consumeFirstValue()
	{
		if(_timeSeries.size() == 0)
		{
			return null;
		}
		PhysicalQuantity first = _timeSeries.get(0);
		_timeSeries.remove(0);
		return first;
	}
}
