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
import { ChevronDownIcon } from "@chakra-ui/icons";

const Task = (props) => {
  return (
    <Card minWidth={"100%"}>
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
                    <MenuItem>Delete</MenuItem>
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
