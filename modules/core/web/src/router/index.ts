import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/ViewHome.vue";
import ViewModeling from "../views/ViewModeling.vue";
import ViewChecker from "../views/ViewChecker.vue";
import ViewImpressum from "../views/ViewImpressum.vue";
import ViewDatenschutz from "../views/ViewDatenschutz.vue";
import ViewLogin from "../views/ViewLogin.vue";
import ViewProfile from "../views/ViewProfile.vue";
import ViewCourses from "../views/ViewCourses.vue";
import ViewTestTask from "../views/ViewTestTask.vue";
import ViewCourseSignup from "../views/ViewCourseSignup.vue";
import ViewCourse from "../views/ViewCourse.vue";
import ViewTask from "../views/ViewTask.vue"
import ViewIntroduction from "../views/ViewIntroduction.vue"
import View404Page from "../views/View404Page.vue"

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/home",
      name: "home",
      component: HomeView,
    },
    {
      path: "/modeling",
      name: "modeling",
      component: ViewModeling,
    },
    {
      path: "/checker",
      name: "ViewChecker",
      component: ViewChecker,
    },
    {
      path: "/impressum",
      name: "ViewImpressum",
      component: ViewImpressum,
    },
    {
      path: "/datenschutz",
      name: "ViewDatenschutz",
      component: ViewDatenschutz,
    },
    {
      path: "/login",
      name: "ViewLogin",
      component: ViewLogin,
    },
    {
      path: "/profile",
      name: "ViewProfile",
      component: ViewProfile,
    },
    {
      path: "/courses",
      name: "ViewCourses",
      component: ViewCourses,
    },
    {
      path: "/testTask",
      name: "testTask",
      component: ViewTestTask,
    },
    {
      path: "/course/:id/signup",
      name: "ViewCourseSignup",
      component: ViewCourseSignup,
    },
    {
      path: "/course/:id",
      name: "ViewCourse",
      component: ViewCourse,
    },
    {
    path: "/course/:courseId/task/:taskId",
    name: "ViewTask",
    component: ViewTask,
    },
    {
    path: "/",
    name: "ViewIntroduction",
    component: ViewIntroduction,
    },
    {
      path: "/:pathMatch(.*)*",
      name: "View404Page",
      component: View404Page,
    },
  ],
});

router.beforeEach((to, from, next) => {
  const publicPages = ["/login"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  const nonAdminPages = ["/modeling", "/login", "/impressum", "/datenschutz", "/404", "/"];
  const adminRequired = !nonAdminPages.includes(to.path);
  const role = localStorage.getItem("role");
  const admin = role?.includes("ADMIN")

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next("/login");
  } else {
    if(adminRequired && !admin){
      next("/404")
    } else {
      next();
    }   
  }
});

export default router;
