import {
  Box,
  Button,
  HStack,
  IconButton,
  Spacer,
  Text,
  useDisclosure,
  VStack,
} from "@chakra-ui/react";
import { DeleteIcon } from "@chakra-ui/icons";
import Task from "./Task";
import TaskModal from "./TaskModal";
import { useState } from "react";

const Board = (props) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleModal = () => {
    setIsOpen(!isOpen);
  };
  //const { isOpen, onOpen, onClose } = useDisclosure();
  console.log(props.tasks);
  return (
    <>
      <Box
        sx={{
          backgroundColor: "red",
          minWidth: "16em",
          minHeight: "30em",
          p: 1,
        }}
      >
        <HStack backgroundColor={"pink.400"} minHeight={10}>
          <Text sx={{ backgroundColor: "brown" }}>{props.title}</Text>
          <Spacer />
          <HStack>
            <IconButton
              colorScheme="blue"
              aria-label="Search database"
              icon={<DeleteIcon />}
            />
          </HStack>
        </HStack>
        <VStack>
          {props.tasks.map((e) => {
            return <Task key={e.id} id={e.id} title={e.title}></Task>;
          })}
          <Button onClick={toggleModal} mt={2} size={"xs"}>
            new task
          </Button>
          <Button
            onClick={() => {
              console.log(props.id);
              console.log(props.tasks);
            }}
            mt={2}
            size={"xs"}
          >
            test
          </Button>
        </VStack>
      </Box>
      <TaskModal
        toggleModal={toggleModal}
        kanbanId={props.id}
        isOpen={isOpen}
        // onClose={onClose}
      />
    </>
  );
};
export default Board;
