import React, { useContext, useState } from "react";
import axios from "axios";
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
import { GlobalCurrentKanbanStateContext } from "../store/CurrentKanbanContext";
import { useLocalStorage } from "../hooks/useLocalStorage";
import { GlobalSwimLaneStateContext } from "../store/SwimLaneConetext";

const TaskModal = ({ swimLaneID, isOpen, onClose }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [currentKanban, setCurrentKanban] = useContext(
    GlobalCurrentKanbanStateContext
  );
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );
  const [localSwimLane, setLocalSwimLane] = useLocalStorage(
    currentSwimLane,
    "swimLane"
  );

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleOnSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(`api/task/addNewTask/${swimLaneID}`, {
        title: title,
        description: description,
        status: "DONE",
      });

      const updatedKanban = { ...currentKanban };
      const updatedSwimLane = { ...currentSwimLane };

      for (let i = 0; i < updatedKanban.swimLanes.length; i++) {
        if (updatedKanban.swimLanes[i].id.toString() === swimLaneID) {
          updatedKanban.swimLanes[i].tasks.push(response.data);
          updatedSwimLane[swimLaneID].tasks.push(response.data);
        }
      }

      setCurrentKanban(updatedKanban);
      setCurrentSwimLane(updatedSwimLane);

      setLocalSwimLane(updatedSwimLane);
    } catch (error) {
      console.log(localSwimLane);
      console.error("Error:", error);
    }
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
