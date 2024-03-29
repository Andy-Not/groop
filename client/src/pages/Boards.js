import Board from "../component/Board";
import { HStack, Text } from "@chakra-ui/react";
import { DragDropContext } from "react-beautiful-dnd";
import { useContext } from "react";
import { GlobalSwimLaneStateContext } from "../store/SwimLaneConetext";
import { GlobalKanbanStateContext } from "../store/KanbanContext";
import axios from "axios";

const moveTask = (swimLaneId, taskId) => {
  axios
    .put(`api/task/moveTaskTo/${swimLaneId}?taskID=${taskId}`)
    .then((res) => {
      console.log(res);
    });
};

const onDragEnd = (result, columns, setColumns) => {
  if (!result.destination) return;
  const { source, destination } = result;
  if (source.droppableId !== destination.droppableId) {
    const sourceColumn = columns[source.droppableId];
    const destColumn = columns[destination.droppableId];
    const sourceItems = [...sourceColumn.tasks];
    const destItems = [...destColumn.tasks];
    const [removed] = sourceItems.splice(source.index, 1);
    moveTask(result.destination.droppableId, result.draggableId);
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
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );
  const [globalKanban] = useContext(GlobalKanbanStateContext);
  return (
    <>
      <HStack maxW={"full"} overflow={"scroll"}>
        {globalKanban.length === 0 ? (
          <Text>NO PROJECTS HAVE BEEN CREATED</Text>
        ) : (
          <DragDropContext
            onDragEnd={(result) =>
              onDragEnd(result, currentSwimLane, setCurrentSwimLane)
            }
          >
            {Object.entries(currentSwimLane).map(
              ([kanbanId, kanban], index) => {
                return (
                  <Board
                    id={currentSwimLane}
                    key={kanbanId}
                    swimLaneID={kanbanId}
                    kanban={kanban}
                  />
                );
              }
            )}
          </DragDropContext>
        )}
      </HStack>
    </>
  );
};
export default Boards;
