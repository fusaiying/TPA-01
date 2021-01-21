export default {
  set: function(name, value) {
    window.localStorage.setItem(name, value);
  },
  get: function(name) {
    return window.localStorage.getItem(name);
  },
  remove: function(name) {
    window.localStorage.removeItem(name);
  },
};