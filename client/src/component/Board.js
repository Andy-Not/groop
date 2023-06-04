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

const Board = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const addNewTask = () => {};
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
            return <Task id={e.id} title={e.title}></Task>;
          })}
          <Button onClick={onOpen} mt={2} size={"xs"}>
            new task
          </Button>
        </VStack>
      </Box>
      <TaskModal isOpen={isOpen} onClose={onClose} />
    </>
  );
};
export default Board;
