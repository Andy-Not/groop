import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  Modal,
  ModalCloseButton,
  ModalContent,
  ModalOverlay,
  Stack,
  Textarea,
  useColorModeValue,
} from "@chakra-ui/react";
import { useState } from "react";
import axios from "axios";

const TaskModal = ({ swimLaneID, isOpen, onClose }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleOnSubmit = (event) => {
    event.preventDefault();
    axios
      .post(`api/task/addNewTask/${swimLaneID}`, {
        title: title,
        description: description,
        status: "DONE",
      })
      .then((e) => {
        console.log(e.data);
      });
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalCloseButton />
        <Box
          rounded={"lg"}
          bg={useColorModeValue("white", "gray.700")}
          boxShadow={"lg"}
          p={8}
        >
          <form onSubmit={handleOnSubmit}>
            <Stack spacing={4}>
              <FormControl id="taskTitle">
                <FormLabel>Title</FormLabel>
                <Input onChange={handleTitleChange} required type="text" />
              </FormControl>
              <FormControl id="taskDescription">
                <FormLabel>Description</FormLabel>
                <Textarea onChange={handleDescriptionChange} type="text" />
              </FormControl>
              <Stack spacing={10}>
                <Button
                  type={"submit"}
                  bg={"blue.400"}
                  color={"white"}
                  _hover={{
                    bg: "blue.500",
                  }}
                >
                  CREATE
                </Button>
              </Stack>
            </Stack>
          </form>
        </Box>
      </ModalContent>
    </Modal>
  );
};
export default TaskModal;
