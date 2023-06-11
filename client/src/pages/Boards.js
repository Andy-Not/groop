import { useEffect, useState } from "react";
import axios from "axios";
import Board from "../component/Board";
import { HStack } from "@chakra-ui/react";
import { DragDropContext } from "react-beautiful-dnd";

const onDragEnd = (result, kanbans, setKanbans) => {
  if (!result.destination) return;
  const { source, destination } = result;
  if (source.droppableId !== destination.droppableId) {
    const sourceColumn = kanbans[source.droppableId];
    const destColumn = kanbans[destination.droppableId];
    const sourceItems = [...sourceColumn.tasks];
    const destItems = [...destColumn.tasks];
    const [removed] = sourceItems.splice(source.index, 1);
    destItems.splice(destination.index, 0, removed);
    setKanbans({
      ...kanbans,
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
    const kanban = kanbans[source.droppableId];
    const copiedItems = [...kanban.tasks];
    const [removed] = copiedItems.splice(source.index, 1);
    copiedItems.splice(destination.index, 0, removed);
    setKanbans({
      ...kanbans,
      [source.droppableId]: {
        ...kanban,
        tasks: copiedItems,
      },
    });
  }
};

const Boards = () => {
  const [kanbans, setKanbans] = useState({});

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
      setKanbans(groupedObjects);
    });
  }, []);
  return (
    <HStack>
      <DragDropContext onDragEnd={onDragEnd}>
        {Object.entries(kanbans).map(([kanbanId, kanban], index) => {
          return <Board key={kanbanId} kanbanId={kanbanId} kanban={kanban} />;
        })}
      </DragDropContext>
    </HStack>
  );
};
export default Boards;
