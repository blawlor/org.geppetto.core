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

import java.util.ArrayList;
import java.util.List;

import org.geppetto.core.visualisation.model.Point;

public class EntityNode extends ACompositeStateNode{

	protected List<AspectNode> aspects =new ArrayList<AspectNode>();;

	private String id;

	private String instancePath;
	
	public String getInstancePath() {
		return instancePath;
	}

	public void setInstancePath(String instancePath) {
		this.instancePath = instancePath;
	}

	public void setAspects(List<AspectNode> aspects) {
		this.aspects = aspects;
	}

	private List<Connection> connections;

	private AMetadataNode metadata;

	private Point position;

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
	public List<AspectNode> getAspects() {
		return aspects;
	}
		
	public void setId(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public static class Connection{
		
		public enum CONNECTION_TYPE
		{
			FROM,
			TO,
			UNDIRECTED
		};
		
		private String id;

		private EntityNode entity;
		
		public void setId(String id){
			this.id = id;
		}
		
		public String getId() {
			return this.id;
		}
		
		public void setEntityNode(EntityNode entity){
			this.entity = entity;
		}
		
		public EntityNode getEntityNode(){
			return this.entity;
		}

		public void setEntityId(String entityID) {
			// TODO Auto-generated method stub
			
		}

		public void setMetadata(AMetadataNode mPost) {
			// TODO Auto-generated method stub
			
		}

		public void setType(String type) {
			// TODO Auto-generated method stub
			
		}
	}

	public AMetadataNode getMetadata() {
		return this.metadata;
	}

	public void setMetadata(AMetadataNode textMetadataNode) {
		
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}