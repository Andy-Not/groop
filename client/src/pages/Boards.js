import { useContext, useEffect, useState } from "react";
import axios from "axios";
import Board from "../component/Board";
import { HStack } from "@chakra-ui/react";
import { DragDropContext } from "react-beautiful-dnd";

const onDragEnd = (result, columns, setColumns) => {
  if (!result.destination) return;
  const { source, destination } = result;
  if (source.droppableId !== destination.droppableId) {
    console.log(columns[destination.droppableId]);
    const sourceColumn = columns[source.droppableId];
    const destColumn = columns[destination.droppableId];
    const sourceItems = [...sourceColumn.tasks];
    const destItems = [...destColumn.tasks];
    const [removed] = sourceItems.splice(source.index, 1);
    destItems.splice(destination.index, 0, removed);
    setColumns({
      ...columns,
      [source.droppableId]: {
        ...sourceColumn,
        tasks: sourceItems,
      },
      [destination.droppableId]: {
        ...destColumn,
        tasks: destItems,
      },
    });
  } else {
    const kanban = columns[source.droppableId];
    const copiedItems = [...kanban.tasks];
    const [removed] = copiedItems.splice(source.index, 1);
    copiedItems.splice(destination.index, 0, removed);
    setColumns({
      ...columns,
      [source.droppableId]: {
        ...kanban,
        tasks: copiedItems,
      },
    });
  }
};

const Boards = () => {
  const [columns, setColumns] = useState({});
  useEffect(() => {
    console.log("use effect ran call was made");
    const boards = [];

    axios.get("api/kanban/getAllKanban").then((e) => {
      e.data.forEach((e) => {
        boards.push(e);
      });
      const groupedObjects = boards.reduce((group, obj) => {
        group[obj.id] = obj;
        return group;
      }, {});
      setColumns(groupedObjects);
    });
  }, []);
  return (
    <HStack>
      <DragDropContext
        onDragEnd={(result) => onDragEnd(result, columns, setColumns)}
      >
        {Object.entries(columns).map(([kanbanId, kanban], index) => {
          return <Board key={kanbanId} kanbanId={kanbanId} kanban={kanban} />;
        })}
      </DragDropContext>
    </HStack>
  );
};
export default Boards;
