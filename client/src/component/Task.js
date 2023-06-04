import {
  Box,
  Button,
  Card,
  CardBody,
  Hide,
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

const Task = (props) => {
  const [isHidden, setIsHidden] = useState(false);
  const element = document.getElementById(props.id);
  if (isHidden) {
    element.remove();
  }
  return (
    <Card id={props.id} minWidth={"100%"}>
      <CardBody p={3}>
        <Stack divider={<StackDivider />}>
          <Box>
            <Text fontSize="sm">{props.title}</Text>
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
                        axios.delete(`api/task/${props.id}`).then(() => {
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
