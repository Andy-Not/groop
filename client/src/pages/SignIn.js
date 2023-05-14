import {
  Flex,
  VStack,
  Box,
  Heading,
  Link,
  InputGroup,
  InputLeftElement,
  Input,
  Stack,
  InputRightElement,
  Button,
  FormControl,
} from "@chakra-ui/react";

const SignIn = () => {
  return (
    <Flex backgroundColor={"gray.200"}>
      <VStack>
        <Heading>GR.OOP</Heading>
        <Box>
          <Stack>
            <FormControl>
              <InputGroup>
                <InputLeftElement pointerEvents="none" children={"S"} />
                <Input type="text" placeholder="Username" />
              </InputGroup>
            </FormControl>
            <FormControl>
              <InputGroup>
                <InputLeftElement pointerEvents="none" children={"S"} />
                <Input type="password" placeholder="Username" />
                <InputRightElement pointerEvents={<Button>s</Button>} />
              </InputGroup>
            </FormControl>
          </Stack>
        </Box>
        <Link color={"red.300"}>Create a new account</Link>
      </VStack>
    </Flex>
  );
};
export default SignIn;
