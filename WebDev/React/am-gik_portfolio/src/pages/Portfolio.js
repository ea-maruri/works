// modules/Repos.js
import React from "react";

// Components
import CommonElements from "./../components/_common-elements/common-elements";
import Footer from "./../components/footer/footer";
import PortfolioCard from "./../components/portfolio-card/portfolio-card";

// Images
import imgPortfolio from "./public/imgs/portfolio/web/home.png";
import imgBasicWeb from "./public/imgs/portfolio/web/basic_home.png";
import imgDoublyLinked from "./public/imgs/portfolio/programming/DoublyLinked.png";
import img8Queens from "./public/imgs/portfolio/programming/8queens_solved.png";
import img8Puzzle from "./public/imgs/portfolio/ai/8puzzle.png";
import img8QueenAI from "./public/imgs/portfolio/ai/8queens.png";
import imgTicTacToe from "./public/imgs/portfolio/ai/tictactoe.png";
import imgVacuumWorld from "./public/imgs/portfolio/ai/vacuumworld.png";
import imgARPProtocol from "./public/imgs/portfolio/networking/arp-protocol.png";
import imgPortrait from "./public/imgs/portfolio/design/portrait.png";

class Portfolio extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      containerArrowPosition: "",
      arrowDirection: "",
    };

    // Call Function
    //this.checkScrollDirection();
    setInterval(this.checkScrollDirection, 500);
  }

  // Check Scrolling
  checkScrollDirection = () => {
    // Sizes
    let body = document.body;
    let html = document.documentElement;
    let refPoint = 2.5;

    const height = Math.max(
      body.scrollHeight,
      body.offsetHeight,
      html.clientHeight,
      html.scrollHeight,
      html.offsetHeight
    );

    // Each half second

    let scrollTop =
      window.pageYOffset ||
      (document.documentElement || document.body.parentNode || document.body)
        .scrollTop;

    if (scrollTop >= height / refPoint) {
      this.setState({
        containerArrowPosition: "flex-column-reverse",
        arrowDirection: "arrow up",
      });
    } else {
      this.setState({
        containerArrowPosition: "flex-column",
        arrowDirection: "arrow down",
      });
    }
  };

  render() {
    // Web Dev
    const portfolioContent = (
      <p className="card-text">
        A brand portfolio for Alejandro Maruri.
        <br></br>
        <br></br>
        It is deployed on <strong>github pages</strong>. You are using it now.
        <br></br>
        <br></br>
        Many knowledge in HTML, CSS, SASS, and JS is used.
      </p>
    );
    const portfolioTechs = <div>HTML, SASS, CSS, Bootstrap, JS</div>;

    const basicWebContent = (
      <p className="card-text">
        A very simple and basic website using Bootstrap, and SASS
      </p>
    );
    const basicWebTechs = <div>HTML, SASS, CSS, Bootstrap</div>;

    // Programming
    const doublyListContent = (
      <p className="card-text">
        A Java program to show Doubly Linked List data structure.
      </p>
    );
    const doublyListTechs = <div>Java, Objects, Classes, Data Structure</div>;

    const eightQueensContent = (
      <p className="card-text">
        A C++ program for resolving 8 Queens Problem by brute force.
        <br></br>
        <br></br>
        The eight queens puzzle is the problem of placing eight chess queens on
        an 8×8 chessboard so that no two queens threaten each other; thus, a
        solution requires that no two queens share the same row, column, or
        diagonal.
      </p>
    );
    const eightQueensTechs = <div>C++, Arrays, Matrix</div>;

    // IA
    const eightPuzzleIAContent = (
      <p className="card-text">
        The 8-puzzle is a square board with 9 positions, filled by 8 numbered
        tiles and one gap. At any point, a tile adjacent to the gap can be moved
        into the gap, creating a new gap position. In other words the gap can be
        swapped with an adjacent (horizontally and vertically) tile. The
        objective in the game is to begin with an arbitrary configuration of
        tiles, and move them so as to get the numbered tiles arranged in
        ascending order either running around the perimeter of the board or
        ordered from left to right, with 1 in the top left-hand position.
      </p>
    );
    const eightPuzzleIATechs = (
      <div>Java, Classes, Objects, IA, A Star Algorithm</div>
    );

    const eightQueensAIContent = (
      <p className="card-text">
        A JAVA program for resolving 8 Queens Problem with{" "}
        <b>genetic algorithm</b>. The eight queens puzzle is the problem of
        placing eight chess queens on an 8×8 chessboard so that no two queens
        threaten each other; thus, a solution requires that no two queens share
        the same row, column, or diagonal.
      </p>
    );
    const eightQueensTechsAI = (
      <div>Java, Classes, Objects, IA, Genetic Algorithm</div>
    );

    const tictactoeContentIA = (
      <p className="card-text">
        A Java program which uses minimax algorithm to play{" "}
        <strong>tic tac toe.</strong>
      </p>
    );
    const tictactoeTechsIA = (
      <div>Java, Classes, Objects, IA, Minimax Algorithm</div>
    );

    const vacuumWorldContent = (
      <p className="card-text">
        A Java program which simulates a vaccum on a square room with obstacles.
      </p>
    );
    const vacuumWorldTechs = <div>Java, Classes, Objects, IA, Algorithm</div>;

    // Networking
    const arpProtocolContent = (
      <p className="card-text">
        A Python program using ARP Protocol to determine NIC (Network Interface
        Card) of some devices.
        <br></br> <br></br>
        Also, a pie image is displayed. Showing vendor percentages.
      </p>
    );
    const arpProtocolTechs = <div>Python, Networking, ARP, Matplotlib</div>;

    // Design
    const portraitContent = (
      <p className="card-text">A basic portrait made in Illustrator.</p>
    );
    const portraitTechs = <div>Illustrator, Design</div>;

    return (
      <div>
        <CommonElements
          containerArrowPosition={this.state.containerArrowPosition}
          arrowDirection={this.state.arrowDirection}
        />

        <div id="document-top"></div>

        <header className="container top-space">
          <div className="row">
            <div className="col-sm-12">
              <h1 className="mtitle text-center top-space">My Work</h1>
            </div>
          </div>
        </header>

        <main className="container bottom-space mx-auto">
          <div className="row">
            <div id="web-section" className="col-sm-12 col-md-6">
              <header className="text-center my-5">
                <h2>Web Development</h2>
              </header>

              <PortfolioCard
                cardId="webAccordion"
                headerId="webAccordionHeader"
                contentId="webAccordionContent"
                dataParent="#webAccordion"
                href="#webAccordionContent"
                ariaControls="webAccordionContent"
                ariaLabelledBy="webAccordionHeader"
                cardTitle="AM-GIK Portfolio"
                innerCardTitle="Portfolio"
                imgSrc={imgPortfolio}
                imgAlt="Portfolio img"
                content={portfolioContent}
                listTechs={portfolioTechs}
                buttonText="Go to Home"
                buttonImgClass="fa fa-home"
                buttonTarget="_blank"
                buttonRef="./../../index.html"
              />

              <PortfolioCard
                cardId="webAccordion1"
                headerId="webAccordion1Header"
                contentId="webAccordion1Content"
                dataParent="#webAccordion1"
                href="#webAccordion1Content"
                ariaControls="webAccordion1Content"
                ariaLabelledBy="webAccordion1Header"
                cardTitle="Basic Webpage"
                innerCardTitle="Webpage"
                imgSrc={imgBasicWeb}
                imgAlt="Web Page"
                content={basicWebContent}
                listTechs={basicWebTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/WebDev/Basic_WebSite"
              />
            </div>

            <div id="prog-section" className="col-sm-12 col-md-6">
              <header className="text-center my-5">
                <h2>Programming</h2>
              </header>

              <PortfolioCard
                cardId="progAccordion"
                headerId="progAccordionHeader"
                contentId="progAccordionContent"
                dataParent="#progAccordion"
                href="#progAccordionContent"
                ariaControls="progAccordionContent"
                ariaLabelledBy="progAccordionHeader"
                cardTitle="Doubly Linked List"
                innerCardTitle="Doubly Linked List"
                imgSrc={imgDoublyLinked}
                imgAlt="D-L List"
                content={doublyListContent}
                listTechs={doublyListTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Programming/Java/Data_Sctructures/Doubly_Linked_List/src/src"
              />

              <PortfolioCard
                cardId="progAccordion1"
                headerId="progAccordion1Header"
                contentId="progAccordion1Content"
                dataParent="#progAccordion1"
                href="#progAccordion1Content"
                ariaControls="progAccordion1Content"
                ariaLabelledBy="progAccordion1Header"
                cardTitle="8 Queens Problem"
                innerCardTitle="8 Queens (Brute Force)"
                imgSrc={img8Queens}
                imgAlt="8 Queens"
                content={eightQueensContent}
                listTechs={eightQueensTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Programming/C%2B%2B/Basic/Chess"
              />
            </div>
          </div>

          <div className="row mt-5">
            <div id="ai-section" className="col-sm-12 col-md-6">
              <header className="text-center my-5">
                <h2>Artificial Intelligence</h2>
              </header>

              <PortfolioCard
                cardId="iaAccordion"
                headerId="iaAccordionHeader"
                contentId="iaAccordionContent"
                dataParent="#iaAccordion"
                href="#iaAccordionContent"
                ariaControls="iaAccordionContent"
                ariaLabelledBy="iaAccordionHeader"
                cardTitle="8 Puzzle Problem"
                innerCardTitle="8 Puzzle"
                imgSrc={img8Puzzle}
                imgAlt="8 Puzzle"
                content={eightPuzzleIAContent}
                listTechs={eightPuzzleIATechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Artificial_Intelligence/8Puzzle_AStar"
              />

              <PortfolioCard
                cardId="iaAccordion1"
                headerId="iaAccordion1Header"
                contentId="iaAccordion1Content"
                dataParent="#iaAccordion1"
                href="#iaAccordion1Content"
                ariaControls="iaAccordion1Content"
                ariaLabelledBy="iaAccordion1Header"
                cardTitle="8 Queens Problem"
                innerCardTitle="8 Queens"
                imgSrc={img8QueenAI}
                imgAlt="8 Queens"
                content={eightQueensAIContent}
                listTechs={eightQueensTechsAI}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Artificial_Intelligence/8Queens"
              />

              <PortfolioCard
                cardId="iaAccordion2"
                headerId="iaAccordion2Header"
                contentId="iaAccordion2Content"
                dataParent="#iaAccordion2"
                href="#iaAccordion2Content"
                ariaControls="iaAccordion2Content"
                ariaLabelledBy="iaAccordion2Header"
                cardTitle="Tic Tac Toe"
                innerCardTitle="Tic Tac Toe"
                imgSrc={imgTicTacToe}
                imgAlt="Tic Tac Toe"
                content={tictactoeContentIA}
                listTechs={tictactoeTechsIA}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Artificial_Intelligence/TicTacToe"
              />

              <PortfolioCard
                cardId="iaAccordion3"
                headerId="iaAccordion3Header"
                contentId="iaAccordion3Content"
                dataParent="#iaAccordion3"
                href="#iaAccordion3Content"
                ariaControls="iaAccordion3Content"
                ariaLabelledBy="iaAccordion3Header"
                cardTitle="Vacuum World"
                innerCardTitle="The Vacuum World"
                imgSrc={imgVacuumWorld}
                imgAlt="Vacuum"
                content={vacuumWorldContent}
                listTechs={vacuumWorldTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Artificial_Intelligence/Vacuum_World"
              />
            </div>

            <div id="net-section" className="col-sm-12 col-md-6">
              <header className="text-center my-5">
                <h2>Networking</h2>
              </header>

              <PortfolioCard
                cardId="netAccordion"
                headerId="netAccordionHeader"
                contentId="netAccordionContent"
                dataParent="#netAccordion"
                href="#netAccordionContent"
                ariaControls="netAccordionContent"
                ariaLabelledBy="netAccordionHeader"
                cardTitle="ARP Protocol"
                innerCardTitle="ARP Protocol (checking devices)"
                imgSrc={imgARPProtocol}
                imgAlt="ARP Protocol"
                content={arpProtocolContent}
                listTechs={arpProtocolTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Networking/ARP_Protocol/ARP_Protocol"
              />
            </div>
          </div>

          <div className="row mt-5">
            <div id="des-section" className="col"></div>

            <div className="col-sm-10">
              <header className="text-center my-5">
                <h2>Design</h2>
              </header>

              <PortfolioCard
                cardId="desAccordion"
                headerId="desAccordionHeader"
                contentId="desAccordionContent"
                dataParent="#desAccordion"
                href="#desAccordionContent"
                ariaControls="desAccordionContent"
                ariaLabelledBy="desAccordionHeader"
                cardTitle="Portrait"
                innerCardTitle="Portrait"
                imgSrc={imgPortrait}
                imgAlt="Portrait"
                content={portraitContent}
                listTechs={portraitTechs}
                buttonText="See on Github"
                buttonImgClass="fa fa-github"
                buttonTarget="_blank"
                buttonRef="https://github.com/ea-maruri/works/tree/master/Design/Illustrator/Self_Portrait"
              />
            </div>

            <div className="col"></div>
          </div>
        </main>

        <Footer />
      </div>
    );
  }
}

export default Portfolio;
