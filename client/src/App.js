import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
function App() {
  return (
    <>
      <SidebarWithHeader>
        <Boards />
      </SidebarWithHeader>
    </>
  );
}

export default App;
