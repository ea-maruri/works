console.log("Main JQ");

// Sizes
let body = document.body;
let html = document.documentElement;

const height = Math.max(
  body.scrollHeight,
  body.offsetHeight,
  html.clientHeight,
  html.scrollHeight,
  html.offsetHeight
);

const width = Math.max(
  body.scrollWidth,
  body.offsetWidth,
  html.clientWidth,
  html.scrollWidth,
  html.offsetWidth
);


function checkScrollDirectionForNav() {
  const width = Math.max(
    body.scrollWidth,
    body.offsetWidth,
    html.clientWidth,
    html.scrollWidth,
    html.offsetWidth
  );
  
  // Check Scroll Direction
  var lastScrollTop = 0;
  var nav_cards = document.getElementById("mprincipal-nav");

  window.addEventListener(
    "scroll",
    function () {
      // Credits: "https://github.com/qeremy/so/blob/master/so.dom.js#L426"
      var st = window.pageYOffset || document.documentElement.scrollTop;
      if (st > lastScrollTop) {
        console.log("Scroll to down");
        if (width < 770) {
          nav_cards.style.bottom = "-200px";
        } else {
          nav_cards.style.bottom = "-260px";
        }
      } else {
        console.log("Scroll to Up");
        if (width < 770) {
          nav_cards.style.bottom = "-160px";
        } else {
          nav_cards.style.bottom = "-210px";
        }
      }

      lastScrollTop = st <= 0 ? 0 : st; // For Mobile or negative scrolling
    },
    false
  );
}
