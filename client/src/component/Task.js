import {
  Box,
  Button,
  Card,
  CardBody,
  HStack,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Stack,
  StackDivider,
  Text,
} from "@chakra-ui/react";
import axios from "axios";
import { useState } from "react";

const Task = ({ id, index, provided, title }) => {
  const [isHidden, setIsHidden] = useState(false);
  const element = document.getElementById(id);
  if (isHidden) {
    element.remove();
  }
  return (
    <Card
      key={id}
      {...provided.dragHandleProps}
      {...provided.draggableProps}
      ref={provided.innerRef}
      _hover={{ cursor: "grab" }}
      id={id}
      index={index}
      w={"100%"}
      maxW={"15em"}
    >
      <CardBody p={3}>
        <Stack divider={<StackDivider />}>
          <Box>
            <Text fontSize="sm">{title}</Text>
          </Box>
          <HStack>
            <Menu>
              {({ isOpen }) => (
                <>
                  <MenuButton
                    size={"sm"}
                    variant={"outline"}
                    isActive={isOpen}
                    as={Button}
                  >
                    options
                  </MenuButton>
                  <MenuList>
                    <MenuItem>Edit</MenuItem>
                    <MenuItem
                      onClick={() => {
                        axios.delete(`api/task/${id}`).then(() => {
                          setIsHidden(true);
                        });
                      }}
                    >
                      Delete
                    </MenuItem>
                  </MenuList>
                </>
              )}
            </Menu>
          </HStack>
        </Stack>
      </CardBody>
    </Card>
  );
};
export default Task;
