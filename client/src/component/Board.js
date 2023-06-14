import {
  Box,
  HStack,
  IconButton,
  Spacer,
  Text,
  useDisclosure,
  VStack,
} from "@chakra-ui/react";
import { DeleteIcon, AddIcon } from "@chakra-ui/icons";
import Task from "./Task";
import TaskModal from "./TaskModal";
import { Draggable, Droppable } from "react-beautiful-dnd";

const Board = ({ kanbanId, kanban }) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <Box
        key={kanbanId}
        _hover={{ cursor: "pointer" }}
        sx={{
          backgroundColor: "#f2f2f2",
          minWidth: "16em",
          minHeight: "30em",
          p: 1,
          borderRadius: 5,
        }}
      >
        <HStack mb={2} minHeight={10}>
          <Text fontWeight={"bold"}>{kanban.title}</Text>
          <Spacer />
          <HStack>
            <IconButton
              variant={"outline"}
              colorScheme="facebook"
              aria-label="Search database"
              icon={<DeleteIcon />}
            />
          </HStack>
        </HStack>
        <Droppable droppableId={kanbanId} key={kanbanId}>
          {(provided) => {
            return (
              <VStack
                width={"100%"}
                minHeight={"100%"}
                {...provided.droppableProps}
                ref={provided.innerRef}
              >
                {kanban.tasks.map((task, index) => {
                  return (
                    <Draggable
                      key={task.id}
                      id={task.id}
                      draggableId={task.id.toString()}
                      index={index}
                    >
                      {(provided) => {
                        return (
                          <Task
                            provided={provided}
                            key={task.id}
                            id={task.id}
                            title={task.title}
                            index={task.index}
                          />
                        );
                      }}
                    </Draggable>
                  );
                })}
                {provided.placeholder}
              </VStack>
            );
          }}
        </Droppable>
        <Box display={"flex"} justifyContent={"center"}>
          <IconButton icon={<AddIcon />} onClick={onOpen} mt={2} size={"sm"} />
        </Box>
      </Box>

      <TaskModal isOpen={isOpen} onClose={onClose} />
    </>
  );
};
export default Board;
