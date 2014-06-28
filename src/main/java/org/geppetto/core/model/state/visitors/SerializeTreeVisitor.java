package org.geppetto.core.model.state.visitors;

import java.util.HashMap;
import java.util.Map;

import org.geppetto.core.model.state.ANode;
import org.geppetto.core.model.state.ACompositeStateNode;
import org.geppetto.core.model.state.ASimpleStateNode;
import org.geppetto.core.model.state.AspectsTreeRoot;
import org.geppetto.core.model.values.AValue;

public class SerializeTreeVisitor extends DefaultStateVisitor
{

	private StringBuilder _serialized = new StringBuilder();

	private Map<ANode, Map<String, Integer>> _arraysLastIndexMap = new HashMap<ANode, Map<String, Integer>>();

	public SerializeTreeVisitor()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inCompositeStateNode(ACompositeStateNode node)
	{

		String name = node.getBaseName();
		if(node.isArray() && node.getParent() != null)
		{
			int index = node.getIndex();
			Map<String, Integer> indexMap = _arraysLastIndexMap.get(node.getParent());

			if(!indexMap.containsKey(name))
			{
				// if the object is not in the map we haven't found this array before
				_serialized.append("{\"" + name + "\":[");
				indexMap.put(name, -1);
			}
			if(indexMap.containsKey(name) && indexMap.get(name) > index)
			{
				throw new RuntimeException("The tree is not ordered, found surpassed index");
			}
			for(int i = indexMap.get(name); i < index - 1; i++)
			{
				// we fill in the gaps with empty objects so that we generate a valid JSON array
				_serialized.append("{},");
			}

			if(!(node.getChildren().get(0) instanceof ACompositeStateNode))
			{
				_serialized.append("{");
			}
			indexMap.put(name, index);
		}
		else
		{
			String namePath = "{\"" + name + "\":";
			ANode parent = node.getParent();

			if(parent != null)
			{
				if(((ACompositeStateNode) parent).getChildren().contains(node))
				{
					if(((ACompositeStateNode) parent).getChildren().indexOf(node) > 0)
					{
						if(_serialized.length() != 0)
						{
							namePath = namePath.replace("{", "");
						}
					}
				}
			}

			_serialized.append(namePath);

			if(node.getChildren().size() > 1)
			{
				if(!(node.getChildren().get(0) instanceof ACompositeStateNode))
				{
					_serialized.append("{");
				}
			}

			// puts bracket around leaf simplestatenode
			else if(node.getChildren().size() == 1)
			{
				if(!(node.getChildren().get(0) instanceof ACompositeStateNode))
				{
					_serialized.append("{");
				}
			}
			else if(node.getChildren().size() == 0)
			{

				_serialized.append("{}");
			}

		}

		_arraysLastIndexMap.put(node, new HashMap<String, Integer>());
		return super.inCompositeStateNode(node);
	}

	@Override
	public boolean outCompositeStateNode(ACompositeStateNode node)
	{

		if(_serialized.toString().endsWith(","))
		{
			_serialized.deleteCharAt(_serialized.lastIndexOf(","));
		}

		if(node.isArray())
		{
			ANode sibling = node.nextSibling();
			if(sibling == null || !(sibling instanceof ACompositeStateNode) || !(((ACompositeStateNode) sibling).getBaseName().equals(node.getBaseName())))
			{

				_serialized.append("}],");
				return super.outCompositeStateNode(node);
			}
			else
			{
				_serialized.append("},");
			}
		}
		else
		{
			if(node.getChildren().size() > 1)
			{

				// no parent means double bracket
				if(node.getParent() == null)
				{
					_serialized.append("}");
				}
				else
				{
					// make sure we didn't go to far, if we did add extra bracket
					if(node.getParent() instanceof AspectsTreeRoot)
					{
						_serialized.append("}");
					}
				}
				_serialized.append("},");
				return super.outCompositeStateNode(node);

			}
			else
			{
				if(!node.getChildren().isEmpty())
				{
					ANode parent = node.getParent();
					if(parent == null || (!(node.getChildren().get(0) instanceof ACompositeStateNode) && (parent instanceof AspectsTreeRoot)))
					{
						_serialized.append("}");
					}
				}
			}

			_serialized.append("},");

		}

		return super.outCompositeStateNode(node);
	}

	@Override
	public boolean visitSimpleStateNode(ASimpleStateNode node)
	{
		AValue value = node.consumeFirstValue();

		String unit = null, scale = null;

		if(node.getUnit() != null)
		{
			unit = "\"" + node.getUnit() + "\"";
		}

		if(node.getScalingFactor() != null)
		{
			scale = "\"" + node.getScalingFactor() + "\"";
		}
		_serialized.append("\"" + node.getName() + "\":{\"value\":" + value + ",\"unit\":" + unit + ",\"scale\":" + scale + "},");

		return super.visitSimpleStateNode(node);
	}

	public String getSerializedTree()
	{
		if(_serialized.charAt(_serialized.length() - 1) == ',') _serialized.deleteCharAt(_serialized.lastIndexOf(","));
		return _serialized.toString();
	}

}
