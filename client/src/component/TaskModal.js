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

  const handleOnSubmit = (event) => {
    event.preventDefault();
    // const indexOfKanban = globalBoardList.findIndex(
    //   (item) => item.id === props.kanbanId
    // );
    // const localBoardArr = globalBoardList;
    //
    // axios
    //   .post(`api/kanban/addTaskToKanban/${props.kanbanId}`, {
    //     title: title,
    //     description: description,
    //     status: "TODO",
    //   })
    //   .then((res) => {
    //     localBoardArr[indexOfKanban].tasks.push(res.data.tasks.slice(-1)[0]);
    //     setGlobalBoardList(localBoardArr);
    //     props.toggleModal();
    //   });
  };

  return (
    <Modal isOpen={props.isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Create a new task</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <form onSubmit={handleOnSubmit}>
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
            <Button onClick={props.toggleModal} mt={2}>
              close
            </Button>
            <Button type={"submit"} mt={2} variant="ghost">
              create
            </Button>
          </form>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
export default TaskModal;
