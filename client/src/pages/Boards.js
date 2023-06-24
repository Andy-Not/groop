import Board from "../component/Board";
import { HStack } from "@chakra-ui/react";
import { DragDropContext } from "react-beautiful-dnd";
import BoardSkeleton from "../component/skeletons/BoardSkeleton";
import { useState } from "react";

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
  return (
    <HStack maxW={"full"} overflow={"scroll"}>
      {Object.keys(columns).length === 0 ? (
        <HStack>
          <BoardSkeleton />
          <BoardSkeleton />
          <BoardSkeleton />
        </HStack>
      ) : (
        <DragDropContext
          onDragEnd={(result) => onDragEnd(result, columns, setColumns)}
        >
          {Object.entries(columns).map(([kanbanId, kanban], index) => {
            return <Board key={kanbanId} kanbanId={kanbanId} kanban={kanban} />;
          })}
        </DragDropContext>
      )}
    </HStack>
  );
};
export default Boards;
