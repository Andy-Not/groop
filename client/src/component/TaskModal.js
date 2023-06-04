import {
  Button,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
} from "@chakra-ui/react";

const TaskModal = (props) => {
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Create a new task</ModalHeader>
        <ModalCloseButton />
        <ModalBody>hello</ModalBody>
        <ModalFooter>
          <Button variant="ghost">CREATE</Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};
export default TaskModal;
