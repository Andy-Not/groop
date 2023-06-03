import {
  Box,
  HStack,
  IconButton,
  Spacer,
  Text,
  VStack,
} from "@chakra-ui/react";
import { DeleteIcon } from "@chakra-ui/icons";

const Board = (props) => {
  return (
    <Box
      sx={{ backgroundColor: "red", minWidth: "16em", minHeight: "30em", p: 1 }}
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
          return <div>{e.title}</div>;
        })}
      </VStack>
    </Box>
  );
};
export default Board;
