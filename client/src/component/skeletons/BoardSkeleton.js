import {
  Box,
  Button,
  Card,
  CardBody,
  HStack,
  Skeleton,
  SkeletonText,
  Spacer,
  Stack,
  StackDivider,
  Text,
  VStack,
} from "@chakra-ui/react";

const BoardSkeleton = () => {
  return (
    <Box
      sx={{
        backgroundColor: "#f2f2f2",
        minWidth: "16em",
        minHeight: "30em",
        p: 1,
        borderRadius: 5,
      }}
    >
      <HStack mb={2} minHeight={10}>
        <Skeleton>
          <Text fontWeight={"bold"}>todo list</Text>
        </Skeleton>
        <Spacer />
        <Skeleton>OPT</Skeleton>
      </HStack>

      <VStack width={"100%"} minHeight={"100%"}>
        <Card w={"100%"} maxW={"15em"}>
          <CardBody p={3}>
            <Stack divider={<StackDivider />}>
              <Box>
                <SkeletonText noOfLines={1}></SkeletonText>
              </Box>
              <HStack>
                <Skeleton>
                  <Button size={"sm"}>options</Button>
                </Skeleton>
              </HStack>
            </Stack>
          </CardBody>
        </Card>
        <Card w={"100%"} maxW={"15em"}>
          <CardBody p={3}>
            <Stack divider={<StackDivider />}>
              <Box>
                <SkeletonText noOfLines={1}></SkeletonText>
              </Box>
              <HStack>
                <Skeleton>
                  <Button size={"sm"}>options</Button>
                </Skeleton>
              </HStack>
            </Stack>
          </CardBody>
        </Card>
        <Card w={"100%"} maxW={"15em"}>
          <CardBody p={3}>
            <Stack divider={<StackDivider />}>
              <Box>
                <SkeletonText noOfLines={1}></SkeletonText>
              </Box>
              <HStack>
                <Skeleton>
                  <Button size={"sm"}>options</Button>
                </Skeleton>
              </HStack>
            </Stack>
          </CardBody>
        </Card>
        <Card w={"100%"} maxW={"15em"}>
          <CardBody p={3}>
            <Stack divider={<StackDivider />}>
              <Box>
                <SkeletonText noOfLines={1}></SkeletonText>
              </Box>
              <HStack>
                <Skeleton>
                  <Button size={"sm"}>options</Button>
                </Skeleton>
              </HStack>
            </Stack>
          </CardBody>
        </Card>
      </VStack>
    </Box>
  );
};
export default BoardSkeleton;
