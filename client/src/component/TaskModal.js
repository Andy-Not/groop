import {
  Button,
  FormControl,
  FormLabel,
  Input,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalHeader,
  ModalOverlay,
} from "@chakra-ui/react";
import axios from "axios";
import { useState } from "react";

const TaskModal = (props) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Create a new task</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <form
            onSubmit={(event) => {
              event.preventDefault();
              axios
                .post(`api/kanban/addTaskToKanban/${props.kanbanId}`, {
                  title: title,
                  description: description,
                  status: "TODO",
                })
                .then(() => props.onClose);
            }}
          >
            <FormControl>
              <FormLabel>Title</FormLabel>
              <Input
                value={title}
                onChange={handleTitleChange}
                name={"title"}
                type={"text"}
              />
            </FormControl>
            <FormControl>
              <FormLabel>Description</FormLabel>
              <Input
                value={description}
                onChange={handleDescriptionChange}
                name={"description"}
                type={"text"}
              />
            </FormControl>
            <Button type={"submit"} mt={2} variant="ghost">
              CREATE
            </Button>
          </form>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
export default TaskModal;
